var express=require('express');
var mysql=require('mysql');
var connection=mysql.createConnection({
    host:'172.20.10.3',
    user:'Project',
    password:'testing00',
    database:'project',
    port:3306
});
var app=express();

connection.connect(function(err){
    if(!err){
        console.log("Connected..\n");
    }
    else{
        console.log("ERR!");
    }
});
app.get("/",function(request,response){
    connection.query('select pc_status from pc where pc_number=1',function(err,rows,fields){
        if(!err){
            response.send(app);
            console.log(':',rows);
        }
        else{
            console.log('ERR!');
        }
        connection.end();
    });
});
  
app.listen(3000);