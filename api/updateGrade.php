<?php

if($_SERVER["REQUEST_METHOD"] == "POST") {
    require 'connection.php';
    createUser();
}

function createUser() {
    global $connect;

    $grade 					= $_POST["grade"];
    $name 				    = $_POST["name"];

    $query = "UPDATE Course SET grade = $grade WHERE name = $name;";
    mysqli_query($connect,$query) or die(mysqli_error($connect));
    mysqli_close($connect);
}

?>
