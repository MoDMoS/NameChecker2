<?php
    require_once "../conn.php";
    require_once "../validate.php";

    $subject = validate($_POST['subject']);
    $id = validate($_POST['id']);
    $sec = validate($_POST['sec']);
    $time = validate($_POST['time']);
    $username = validate($_POST['username']);
    $pass = validate($_POST['pass']);

    $sql1 = mysqli_query($conn,"SELECT Name FROM user WHERE Username = '$username' OR Id = '$username'");
    $result = mysqli_fetch_array($sql1);
    $name = $result[0];

    $sql2 = mysqli_query($conn,"SELECT Subject FROM subject WHERE Subject = '$subject'");
    $row = mysqli_fetch_array($sql2);

    if($row){
        $sql3 = "INSERT INTO section VALUES('$subject', '$sec', '$time', '$name', '$pass')";
        if(!$conn->query($sql3)){
            echo "failure";
        }else{
            echo "success";   
        }
    }else{
        $sql4 = "INSERT INTO subject VALUES('$subject', '$id')";
        if(!$conn->query($sql4)){
            echo "failure1";
        }else{
            $sql5 = mysqli_query($conn,"SELECT Sec FROM section WHERE Subject = '$subject' AND Sec = '$sec'");
            $result = mysqli_fetch_array($sql5);
            if($result){
                echo "failure2";  
            }else{
                $sql6 = "INSERT INTO section VALUES('$subject', '$sec', '$time', '$name', '$pass')";
                mysqli_query($conn,$sql6);
                echo "success";
            }
        }
    }
?>