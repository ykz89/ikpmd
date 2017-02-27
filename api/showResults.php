<?php

if($_SERVER["REQUEST_METHOD"] == "POST") {
    require 'connection.php';
    showResults();
}

function showResults() {
    global $connect;
    $query = "SELECT id, name, ects, term, IF(mandatory, 'true', 'false') as mandatory, grade FROM Course WHERE grade is not null;";
    $result = mysqli_query($connect,$query);
    $number_of_rows = mysqli_num_rows($result);
    $temp_array = array();
    if($number_of_rows > 0) 	{
        while($row = mysqli_fetch_assoc($result))  {
            $temp_array[] = $row;
        }
    }
    if(count($temp_array) < 1){
        $temp_array = 'empty';
    }
    header('Content-type: application/json');
    echo json_encode($temp_array);
    mysqli_close($connect);
}   ?>
