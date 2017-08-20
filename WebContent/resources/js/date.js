/*    function date() {
        $('#checkin').datetimepicker({
        	format: 'DD/MM/YYYY'
        });
        $('#checkout').datetimepicker({
        	format: 'DD/MM/YYYY'
            useCurrent: false //Important! See issue #1075
        });
        $("#checkin").on("dp.change", function (e) {
            $('#checkout').data("DateTimePicker").minDate(e.date);
        });
        $("#checkout").on("dp.change", function (e) {
            $('#checkin').data("DateTimePicker").maxDate(e.date);
        });
    }*/

$(document).ready(function() {
	$( ".datepicker" ).datepicker({
		dateFormat: 'dd/mm/yy',
		changeMonth: true,
		changeYear: true,
		yearRange: "1960:2017"
	});
    
});