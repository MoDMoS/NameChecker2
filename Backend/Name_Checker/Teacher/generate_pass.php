<?php
    require_once "../conn.php";
    require_once "../validate.php";

    $pass = validate($_POST['pass']);
    $subject = validate($_POST['subject']);
    $sec = validate($_POST['sec']);
    $check = validate($_POST['check']);

    if($check == "generate"){
        $sql1 = mysqli_query($conn, "UPDATE section SET Check_In= '$pass' WHERE Subject='$subject' AND Sec='$sec'");
        echo "success";
    }else if($check == "close"){
        $sql2 = mysqli_query($conn, "UPDATE section SET Check_In= ' ' WHERE Subject='$subject' AND Sec='$sec'");
        echo "success";
    }else if($check == "delete"){
        $sql3 = mysqli_query($conn, "DELETE FROM section WHERE Subject='$subject' AND Sec='$sec' ");
        echo "success";
    }else{
        echo "failure";
    }

