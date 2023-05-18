$(document).ready(function () {
	$('#hr-table').DataTable({
		ajax: {
			url: 'api/hr',
			dataSrc: ''
		},
		columns: [{
				data: null,
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			// {
			// 	data: 'id'
			// },
			{
				data: 'name'
			},
			{
				data: 'email'
			},
			{
				data: 'phone'
			}
		]
	});


});

function create() {
	let valName = $('#name').val()
	let valEmail = $('#email').val()
	let valPhone = $('#phone').val()
	let valUsername = $('#username').val()
	let valPass = $('#password').val()

	// console.log(valName);

	$.ajax({
		method: "POST",
		url: "api/hr",
		dataType: "JSON",
		beforeSend: addCsrfToken(),
		data: JSON.stringify({
			name: valName,
			email: valEmail,
			phone: valPhone,
			username: valUsername,
			password: valPass

		}),
		contentType: "application/json",
		success: result => {
			console.log(result)
			$('#addModal').modal('hide')
			$('#hr-table').DataTable().ajax.reload()
			Swal.fire({
				position: 'top-center',
				icon: 'success',
				title: 'HR has been added!',
				showConfirmButton: false,
				timer: 1500,
				customClass: {
					container: 'dark'
				},
			})
		}
	})
}