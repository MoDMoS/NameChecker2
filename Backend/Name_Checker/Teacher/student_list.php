<?php
    require_once "../conn.php";
    require_once "../validate.php";

    $subject = validate($_GET['subject']);
    $sec = validate($_GET['sec']);

    $pdo = new PDO("mysql:host=localhost;dbname=namechecker;charset=utf8", "root", "");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = $pdo->prepare("SELECT * FROM `student_list` WHERE Subject='$subject' AND Sec='$sec'");
    $stmt->execute();

    //===================//
    $resultArray = array();
    while($row = $stmt->fetch()){
        array_push($resultArray, $row);
    }
    echo json_encode($resultArray);
    //===================//
?>