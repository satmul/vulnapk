const mysql = require('mysql');

const connection = mysql.createPool({
    connectionLimit : 10,
    host: 'mysql', 
    user: 'root',
    password: 'root',
    database: 'vulnapk'
  });
  
module.exports = {
    connection : connection
};