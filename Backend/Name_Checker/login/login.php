<?php
if(isset($_POST['username']) && isset($_POST['password'])){
    
    require_once "../conn.php";
    require_once "../validate.php";
    
    $username = validate($_POST['username']);
    $password = validate($_POST['password']);
    
    $sql = "SELECT Role FROM user WHERE (ID = '$username' OR Username='$username') AND Password='$password'";
    
    $result = mysqli_query($conn,$sql);

    $row = mysqli_fetch_array($result);
    $data = $row[0];
    
    if($data){
        if($row[0] == "Student"){
            echo "Student";
        }
        if($row[0] == "Teacher"){
            echo "Teacher";
        }
    } else{
        echo "failure";
    }
}
?>