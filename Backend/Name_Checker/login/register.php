<?php
if(isset($_POST['username']) && isset($_POST['password'])){
    require_once "../conn.php";
    require_once "../validate.php";
    
    $id = validate($_POST['id']);
    $name = validate($_POST['name']);
    $username = validate($_POST['username']);
    $password = validate($_POST['password']);
    $role = validate($_POST['role']);
    
    $sql = "INSERT INTO user VALUES('$id', '$name', '$username', '$password', '$role')";
    
    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";   
    }
}
else{
    echo "not found";
}
?>