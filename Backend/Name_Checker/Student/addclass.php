<?php
    require_once "../conn.php";
    require_once "../validate.php";

    $username = validate($_POST['username']);
    $pass = validate($_POST['pass']);

    $sql1 = mysqli_query($conn,"SELECT Name, Id FROM user WHERE Username = '$username' OR Id = '$username'");
    $result = mysqli_fetch_array($sql1);
    $name = $result[0];
    $id = $result[1];

    $sql2 = mysqli_query($conn,"SELECT Subject, Sec, Time, Name FROM section WHERE Pass='$pass'");
    $result2 = mysqli_fetch_array($sql2);
    if($result2){
        $subject = $result2[0];
        $sql3 = mysqli_query($conn,"SELECT Id FROM subject WHERE Subject='$subject'");
        $result3 = mysqli_fetch_array($sql3);
        $sec = $result2[1];
        $time = $result2[2];
        $teacher = $result2[3];
        $sql2 = "INSERT INTO student_list (`Subject`, `Subject_Id`, `Sec`, `Time`, `Teacher`, `Name`, `Id`, `Count`) VALUES ('$subject', '$result3[0]', '$sec', '$time', '$teacher', '$name', '$id', 0)";
        if(!$conn->query($sql2)){
            echo "failure";
        }else{
            echo "success";   
        }
    }else{
        echo "failure";
    }

