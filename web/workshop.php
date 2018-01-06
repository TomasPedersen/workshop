<?php
// define variables and set to empty values
$name = $phone = $email = "";

// Tag id fra url.
$id = $_GET['id'];

// Opret forbindelse til databasen.
$servername = "patina.dyndns.dk";
$username = "workshop";
$password = "WorkshopPassword";
$dbname = "workshop";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT first_name, phone, email FROM persons WHERE id='".$id."'";
$result = $conn->query($sql);

$row = $result->fetch_assoc();
    // Kopier $row til variable, da $row ikke har noget indhold i html-blokken.
    $name=$row["first_name"];
    $phone=$row["phone"];
    $email=$row["email"];

//<!-- Indsæt værdierne fra databasen i formularen -->
echo "
<html>
<body>
<form method='post' action=".htmlspecialchars($_SERVER["PHP_SELF"]).">
    <input type='hidden' name='id' value='".$id."'><br>
    Navn: <input type='text' name='name' value='"  .htmlentities($name).  "'><br>
    Telefon: <input type='text' name='phone' value='"  .htmlentities($phone).  "'><br>
    E-mail: <input type='text' name='email' value='".htmlentities($email)."'><br>
    <input type='submit'>
</form>
</body>
</html>
";

$html = "<html>";
$slash_html = "</html>";
$br = "<br>";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST["id"];
    $name = test_input($_POST["name"]);
    $phone = test_input($_POST["phone"]);
    $email = test_input($_POST["email"]);

    // TODO: Test at data kan godkendes.

  // Opdater værdier i database.
    $sql="UPDATE persons SET 
    name='".$name."',
    phone='".$phone."',
    email='".$email."'
    WHERE id='".$id."'    
    ";
    echo $sql;

  // Skriv kvittering med nye oplysninger.
  echo "Dine oplysninger blev opdateret".$br;
  echo $html."Navn: ".$name.$br."  Telefon: ".$phone.$br."  Email: ".$email.$slash_html;
}
function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
$conn->close();
?>