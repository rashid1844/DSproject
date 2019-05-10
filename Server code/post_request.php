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
$item_id=sqlblk($data->item_id);
$item_name=sqlblk($data->item_name);
$description=sqlblk($data->description);
$category=sqlblk($data->category);
$prikey=$data->prikey;

if($prikey=="WUHhBpHAwCYSn%+8=Fsy")
{

if ($category=="null")
{

if (($h = fopen("/var/hierarchy_product_description_training.csv", "r")) !== FALSE)
{
$Output="";
$i=0;

$search = preg_split('/\s+/', $item_name);


for ($j = 0; $j < count($search) && strlen($search[$j])>2 ; $j += 1) { 


while (($data = fgetcsv($h, 1000, ",")) !== FALSE || $i<30000)
  {


if(stripos($data[0], $search[$j]) && stripos( $data[1] ,'electronic')>-1)
{$Output="electronics";
break;}
else if(stripos($data[0], $search[$j])>-1 && stripos($data[1] ,'cloth')>-1)
{$Output="clothing";
break;}
else if(stripos($data[0], $search[$j]) && stripos( $data[1] ,'Automotive-Accessories')>-1)
{$Output="automotive accessories";
break;}
else if(stripos($data[0], $search[$j]) && stripos( $data[1] ,'Mobiles_Accessories')>-1)
{$Output="mobiles";
break;}

else if(stripos($data[0], $search[$j]) && stripos( $data[1] ,'Computers')>-1)
{$Output="computers";
break;}

$i+=1;

}
//for loop

if($Output !="")
break;



}
//while loop

if($Output=="")
$category="Others";
else
$category=$Output;
$g="category";
echo $category;
}}
//end of if statment for no category.




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
$sql =  "insert into request (item_id, description, item_name , user, category ,reply) values (\"$item_id\",\"$description\",\"$item_name\",\"$name\",\"$category\",0)" ;
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
