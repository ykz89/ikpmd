<?php

if($_SERVER["REQUEST_METHOD"] == "POST") {
    require 'connection.php';
    updateGrade();
}

function updateGrade()
{
    global $connect;

    if(isset($_SERVER["QUERY_STRING"])) {
        parse_str($_SERVER["QUERY_STRING"]);
        $grade = mysqli_real_escape_string($connect, $grade);
        $name = mysqli_real_escape_string($connect, $name);

        $query = "UPDATE Course SET grade = $grade WHERE name = '$name';";
        mysqli_query($connect, $query) or die(mysqli_error($connect));
        mysqli_close($connect);
        echo "success";
    }
}


?>
