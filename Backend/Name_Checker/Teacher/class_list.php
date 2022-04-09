<?php
    // require_once "../conn.php";
    // require_once "../validate.php";

    // $username = validate($_POST['username']);
    // $arr[] = NULL;

    // $sql1 = mysqli_query($conn,"SELECT Name FROM user WHERE Username = '$username' OR Id = '$username'");
    // $result = mysqli_fetch_array($sql1);
    // $name = $result[0];

    // $sql2 = mysqli_query($conn,"SELECT * FROM section WHERE Name = '$name'");

    // while($r=mysqli_fetch_object($sql2))
    // {
    //     $res[]=$r;
    // }
    // echo json_encode($res);
    $pdo = new PDO("mysql:host=localhost;dbname=namechecker;charset=utf8", "root", "");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = $pdo->prepare("SELECT * FROM section ");
    $stmt->execute();

    //===================//
    $resultArray = array();
    while($row = $stmt->fetch()){
        array_push($resultArray, $row);
    }
    echo json_encode($resultArray);
    //===================//
?>