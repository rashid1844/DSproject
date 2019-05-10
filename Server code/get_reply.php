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
header('HTTP/1.0 200 Ok');


error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "select * from reply where req_user= '".$name;
$sql= $sql. "'";
$result = mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));
$listing = array();
while($row = mysql_fetch_array($result))
{      $current = new stdclass;
       $current->request_id= $row['request_id'];
       $current->item_name = $row['item_name'];
       $current->rep_user = $row['rep_user'];
       $current->stream_id =  $row['stream_id'];
       array_push($listing, $current);}
echo json_encode($listing);

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
