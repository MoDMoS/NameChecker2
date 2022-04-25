<?php
    require_once "../conn.php";
    require_once "../validate.php";

    $subject = validate($_GET['subject']);
    $sec = validate($_GET['sec']);
    $username = validate($_GET['username']);
    $arr[] = NULL;

    $sql1 = mysqli_query($conn,"SELECT Id FROM user WHERE Username = '$username' OR Id = '$username'");
    $result = mysqli_fetch_array($sql1);
    $id = $result[0];


    // $pdo = new PDO("mysql:host=localhost;dbname=namechecker;charset=utf8", "root", "");
    // $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = mysqli_query($conn,"SELECT section.Date, student_list.Last FROM section, student_list WHERE section.Subject='$subject' AND student_list.Subject='$subject' AND student_list.Sec='$sec' AND student_list.Id = '$id'");

    //===================//
    $resultArray = array();
    $row = mysqli_fetch_array($stmt);
    array_push($resultArray, $row);

    echo json_encode($resultArray);
    //===================//
?>