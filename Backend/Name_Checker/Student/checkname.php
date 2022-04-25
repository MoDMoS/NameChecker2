<?php
    require_once "../conn.php";
    require_once "../validate.php";

    $username = validate($_POST['username']);
    $pass = validate($_POST['pass']);
    $date = validate($_POST['date']);

    $sql1 = mysqli_query($conn,"SELECT Name, Id FROM user WHERE Username = '$username' OR Id = '$username'");
    $result = mysqli_fetch_array($sql1);
    $name = $result[0];

    $sql2 = mysqli_query($conn, "SELECT Subject, Sec FROM section WHERE Check_In = '$pass'");
    $result2 = mysqli_fetch_array($sql2);
    
    if($result2){
        $subject = $result2[0];
        $sec = $result2[1];
        $sql3 = mysqli_query($conn, "SELECT Count FROM student_list WHERE Subject = '$subject' AND Sec = '$sec' AND Name = '$name'");
        $result3 = mysqli_fetch_array($sql3);
        $count = $result3[0];
        $count = $count + 1;

        $sql4 = "UPDATE student_list SET Count='$count', Last='$date'  WHERE Subject = '$subject' AND Sec = '$sec' AND Name = '$name'";
        if(!$conn->query($sql4)){
            echo "failure";
        }else{
            echo "success";   
        }
    }else{
        echo "failure";
    }