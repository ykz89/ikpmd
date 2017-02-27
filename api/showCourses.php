<?php

if($_SERVER["REQUEST_METHOD"] == "POST") {
    require 'connection.php';
    showCourses();
}

function showCourses() {
    global $connect;
    $query = "SELECT id, name, ects, term, IF(mandatory, 'true', 'false') as mandatory, grade FROM Course;";
    $result = mysqli_query($connect,$query);
    $number_of_rows = mysqli_num_rows($result);
    $temp_array = array();
    if($number_of_rows > 0) 	{
        while($row = mysqli_fetch_assoc($result))  {
            $temp_array[] = $row;
        }
    }
    header('Content-type: application/json');
    echo json_encode($temp_array);
    mysqli_close($connect);
}   ?>
