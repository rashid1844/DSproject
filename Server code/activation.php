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
$code=sqlblk($data->code);
$prikey=$data->prikey;

if($prikey=="WUHhBpHAwCYSn%+8=Fsy")
{

error_reporting(E_ALL ^ E_DEPRECATED);
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$sql = "select * from users where name= '".$name;
$sql = $sql. "'";


$result = mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));

$listing = array();
while($row = mysql_fetch_array($result))
{
       $verify_atempts = $row['verify_atempts'];
       $verify_code =  $row['verify_code'];
       $verify =  $row['verify'];
	$pass= $row['password'];
}




if($verify==1)
{
echo("Account already activated");
header('HTTP/1.0 208 Already Reported');

}


else if($code==$verify_code and $pass==sha1($password))
{
mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$passwordsha=sha1($password);
$sql= "UPDATE users SET verify = 1 WHERE name=\"$name\"";
mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));
header('HTTP/1.0 202 Accepted');

}


else if($verify_atempts==1 and $pass==sha1($password)){

$code1=mt_rand(1000,9999);

mysql_connect("localhost", "rashid", "12341224") or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());

$sql = "UPDATE users set verify_code =\"$code1\", verify_atempts=5 where name=\"$name\"";
mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));


header('HTTP/1.0 406 Not Acceptable');



        // Authorisation details.
        $username = "rrr111uae@yahoo.com";
        $hash = "037d9b23dffa8ccfe41d2f51ba5b6c4997b24c55923fdd6f2c45c389657e3dc0";

        // Config variables. Consult http://api.txtlocal.com/docs for more info.
        $test = "0";

        // Data for text message. This is the text message data.
        $sender = "streamer App"; // This is who the message appears to be from.
        $numbers = $name; // A single number or a comma-seperated list of numbers
        $message = "Your activiation code is: ".$code1;

        $message = urlencode($message);
        $data = "username=".$username."&hash=".$hash."&message=".$message."&sender=".$sender."&numbers=".$numbers."&test=".$test;
        $ch = curl_init('http://api.txtlocal.com/send/?');
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $result = curl_exec($ch); // This is the result from the API
        curl_close($ch);


}

else if($pass==sha1($password)){

mysql_connect("localhost", $db_user, $db_pass) or die(header('HTTP/1.0 400 Bad Request'));
mysql_select_db("projectdb") or die(mysql_error());
$verify_atempts=$verify_atempts-1;
$sql = "UPDATE users set verify_atempts=\"$verify_atempts\" where name=\"$name\"";
mysql_query($sql) or die(header('HTTP/1.0 400 Bad Request'));
header('HTTP/1.0 401 Unauthorized');

}

else
header('HTTP/1.0 404 Unauthorized');
}
else
header('HTTP/1.0 405 Method Not Allowed');


?>
