const jwt = require('jsonwebtoken');
const secretKey = "alexandra"


function get_token(username,role){    
    const payload = {
        user: username,
        role: role
    };

    const token = jwt.sign(payload, secretKey);

    try {
        return token
    } catch (err) {
        console.error(err);
    }
    
}
   
function check_algo(token){
    let check = null;
    const algorithms = ['none', 'HS256'];
    for(const algorithm of algorithms){
        if (algorithm === 'none') {
            decoded = jwt.decode(token);
            if (decoded) {
                check = algorithm;
              break;
            }
          } else {
            try {
            decoded = jwt.verify(token, secretKey, { algorithms: [algorithm] });
              check = algorithm;
              break;
            } catch (err) {
              console.error(err);
              return 1;
            }
          }
    }
}

function get_role(token){
  var user_role = null;
  decoded = jwt.decode(token)
  user_role = decoded.role;
  console.log(user_role);
  return user_role;
}

module.exports = {
    get_token: get_token,
    check_algo: check_algo,
    get_role: get_role
};