<?php

function sqlblk($str){
 $str= str_replace("=","",$str);
 $str= str_replace("-","",$str);
 $str= str_replace("OR","",$str);
 $str= str_replace(";","",$str);
 $str= str_replace("'","",$str);
 $str= str_replace("AND","",$str);
$str= str_replace("*","",$str);
return $str;
}

include("/var/db_settings.php");

$postdata= file_get_contents("php://input");
$data = json_decode($postdata);

$name=sqlblk($data->name);
$password=sqlblk($data->password);

$request_id=sqlblk($data->request_id);
$item_name=sqlblk($data->item_name);
$req_user=sqlblk($data->req_user);
$stream_id=sqlblk($data->stream_id);


$prikey=$data->prikey;

if($prikey=="WUHhBpHAwCYSn%+8=Fsy")
{

error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "select password from users where name= '".$name;
$sql = $sql. "'";


$result = mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));

$pass= mysql_fetch_array($result);


if($password !=null){
if($pass['password']== sha1($password)){
header('HTTP/1.0 201 Created');


error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql =  "insert into reply (request_id,item_name,rep_user,req_user,stream_id) values (\"$request_id\",\"$item_name\",\"$name\",\"$req_user\",\"$stream_id\")" ;
mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));



mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql =  "update request set reply=1 where request_id='".$request_id;
$sql= $sql."'";
mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));





}
else
header('HTTP/1.0 401 Unauthorized');
}
else
header('HTTP/1.0 400 Bad Request');
}
else
header('HTTP/1.0 405 Method Not Allowed');


?>
