const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const jwt = require('./middleware/auth.js');
const db = require('./middleware/db.js')


app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');


var login_token = null;

// Register
app.post('/register', (req, res) => {
  var username = req.body.username;
  var email = req.body.email;
  var password = req.body.password;
  db.connection.query(`INSERT INTO users (username, email, password) VALUES ('${username}','${email}','${password}')`,(err, result) => {
    if (err) {
      res.status(500).json({ error: err.stack });
      return;
    }
    res.render('login',{ showRegister: true, showLogout:false, showLogin: true, error:null})
  });
});

// Login 
app.post('/login', (req, res) => {
    const { username, password, role } = req.body;
    db.connection.query(`SELECT * FROM users WHERE username = '${username}'`, (err, results) => {
      if (err) {
        res.status(500).json({ error: err.stack});
        return;
      }
      if (results.length === 0) {
        res.render('login',{ showRegister: true, showLogout:false, showLogin: true, error:'Invalid Username / Password'});
      } else {
        const user = results[0];
        if (user.password === password) {
          login_token = jwt.get_token(username,role)
          res.redirect('/products')
        } else {
          res.render('login',{ showRegister: true, showLogout:false, showLogin: true, error:'Invalid Username / Password'});
        }
      }
    });
});

// Routes
app.get('/', (req, res) => {
  res.render('login',{ showRegister: true, showLogout:false, showLogin: true, error:null});
});

app.get('/register', (req, res) => {
  res.render('register', { showRegister: true, showLogout:false, showLogin: true, success:null});
});
 

// Apply the middleware to important endpoints

const checkAuthorization = (req, res, next) => {
  var token = login_token
  if (token && jwt.check_algo(token) != 1) {
    res.set('Authorization', `Bearer ${token}`);
    next();
  } 
  else {
    res.redirect('/')
  }
};

const checkAdmin = (req, res, next) => {
  var token = login_token
  if (jwt.get_role(token) == "admin") {
    next();
  } 
  else {
    res.redirect('/')
  }
};

app.use(checkAuthorization);


// Get all products
app.get('/products', (req, res) => {
  db.connection.query('SELECT * FROM product', (err, results) => {
    if (err) {
      res.redirect('/')
    }
    res.render('index',{products:results, showLogout:true, showRegister: false, showLogin:false,error:null })
  });
});

// Get a specific product
app.get('/product', (req, res) => {
  const id = req.query.id;
  db.connection.query(`SELECT * FROM product WHERE id = '${id}'`,(err, results) => {
    if (err) {
      res.status(500).json({ error: err.stack });
      return;
    }
    if (results.length === 0) {
      res.status(404).json({ error: 'product not found' });
    } else {
      res.json(results[0]);
    }
  });
});


// Search product
app.post('/search', (req, res) => {
  const product_name = req.body.name;
  db.connection.query(`SELECT * FROM product WHERE name = '${product_name}'`,(err, results) => {
    if (err) {
      res.render('index',{products:null, showLogout:true, showRegister: false, showLogin:false, error:err.stack})
      return;
    }
    if (results.length === 0) {
      res.render('index',{error:`product ${product_name} not found`, showLogout:true, showRegister: false, showLogin:false, products:null})

    } else {
      res.render('index',{products:results, showLogout:true,showRegister: false, showLogin:false, error:null })
    }
  });
});



// Admin section

// Insert Product
app.use(checkAdmin);
app.get('/admin', (req, res) => {
  res.render('admin',{showLogout:true, showRegister: false, showLogin:false,success:null})
});

app.post('/insert', (req, res) => {
  var name = req.body.name;
  var price = req.body.price;
  var desc = req.body.desc;
  db.connection.query(`INSERT INTO product (name, price, description) VALUES ('${name}','${price}','${desc}')`,(err, result) => {
    if (err) {
      res.status(500).json({ error: err.stack });
      return;
    }
    res.render('admin',{showLogout:true, showRegister: false, showLogin:false,success:"Input Product Success."})
  });
}); 


// Start the server
app.listen(3000, () => {
  console.log('Server is running on port 3000');
});
