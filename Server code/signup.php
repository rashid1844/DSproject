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
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 401 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "select name from users where name= '".$name;
$sql = $sql. "'";


$result = mysql_query($sql) or die(header('HTTP/1.0 402 Bad Request'));
$arra= mysql_fetch_array($result);



if($arra!=null)
header('HTTP/1.0 409 Conflict');
else
{
$code=mt_rand(1000,9999);

mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 403 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$passwordsha=sha1($password);
$sql = "INSERT INTO users (name,password,verify_code) VALUES (\"$name\",\"$passwordsha\",\"$code\")";
mysql_query($sql) or die(header('HTTP/1.0 404 Bad Request'));


header('HTTP/1.0 202 Accepted');




	// Authorisation details.
	$username = "rrr111uae@yahoo.com";
	$hash = "037d9b23dffa8ccfe41d2f51ba5b6c4997b24c55923fdd6f2c45c389657e3dc0";

	// Config variables. Consult http://api.txtlocal.com/docs for more info.
	$test = "0";

	// Data for text message. This is the text message data.
	$sender = "streamer App"; // This is who the message appears to be from.
	$numbers = $name; // A single number or a comma-seperated list of numbers
	$message = "Your activiation code is: ".$code;

	$message = urlencode($message);
	$data = "username=".$username."&hash=".$hash."&message=".$message."&sender=".$sender."&numbers=".$numbers."&test=".$test;
	$ch = curl_init('http://api.txtlocal.com/send/?');
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$result = curl_exec($ch); // This is the result from the API
	curl_close($ch);




}

}
else
header('HTTP/1.0 405 Method Not Allowed');


?>
