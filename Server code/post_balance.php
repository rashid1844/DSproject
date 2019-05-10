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
$adj=sqlblk($data->adj);
$user=sqlblk($data->user);
$prikey=$data->prikey;

if($prikey=="WUHhBpHAwCYSn%+8=Fsy")
{


error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "select * from users where name= '".$name;
$sql = $sql. "'";

$result = mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));
$listing=array();
while($row = mysql_fetch_array($result))
{     $current= new stdclass;
	$pass= $row['password'];
	$balance1= $row['balance'];
}






error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "select * from users where name= '".$user;
$sql = $sql. "'";

$result = mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));
$listing=array();
while($row = mysql_fetch_array($result))
{     $current= new stdclass;
        $balance2= $row['balance'];
}





if($password !=null){
if($pass== sha1($password)){
header('HTTP/1.0 200 Ok');
$balance1= $balance1 -$adj;
$balance2= $balance2 +$adj*0.95;


//update user1 balace
error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "update users set balance=".$balance1." where name='".$name;
$sql = $sql. "'";
mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));





//update user2 balance
error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "update users set balance=".$balance2." where name='".$user;
$sql = $sql. "'";
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
