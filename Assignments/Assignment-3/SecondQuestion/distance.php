<?php
     $dist = $_POST['dist'];
     $units = $_POST['units'];
     if($units=='kms'){
          $miles = $dist / 1.609;
          echo 'The value of given distance in miles is '.round($miles).' miles.';
     }else{
        $kms = $dist * 1.609;
        echo 'The value of given distance in kms is '.round($kms).' kilometers.';  
     }
?>