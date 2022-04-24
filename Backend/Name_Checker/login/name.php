<?php
    require_once "../conn.php";
    require_once "../validate.php";

    $username = validate($_GET['username']);

    $pdo = new PDO("mysql:host=localhost;dbname=namechecker;charset=utf8", "root", "");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = $pdo->prepare("SELECT Name, Id FROM user WHERE Username = '$username'");
    $stmt->execute();

    $resultArray = array();
    while($row = $stmt->fetch()){
        array_push($resultArray, $row);
    }
    echo json_encode($resultArray);

