$(document).ready(function () {
	$('#mitra-table').DataTable({
		ajax: {
			url: 'api/mitra',
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
				data: 'phone'
			},
			{
				data: 'description'
			},
			{
				data: 'user.isEnabled',
				render: function (data, type, row) {

					if (data === false) {
						return '<label class="badge badge-outline-danger"> Disable </label>';
					} else {
						return '<label class="badge badge-outline-success"> Enable </label>';
					}
				}
			},
			{
				"data": null,
				render: function (data, row, type, meta) {
					return `<button type="button" class="btn btn-warning px-4 mr-3" data-toggle="modal" data-target="#updateModal" onclick="before(${data.id})"> Update </button >`;
				}
			}

		]
	});


});

function before(id) {
	$.ajax({
		method: "GET",
		url: "api/mitra/" + id,
		dataType: "JSON",
		success: function (result) {
			console.log(result)
			$('#id-update').val(`${result.id}`)
			$('#username-update').val(`${result.user.username}`)
			$('#password-update').val(`${result.user.password}`)
			$('#isEnabled-update').val(`${result.user.isEnabled}`)
			$('#isAccountNonLocked-update').val(`${result.user.isAccountNonLocked}`);
		}
	})
}

function update() {
	let valId = $('#id-update').val()
	let valUsername = $('#username-update').val()
	let valPass = $('#password-update').val()
	let valEnable = $('#isEnabled-update').val()
	let valAcc = $('#isAccountNonLocked-update').val()
	// console.log(valName);

	$.ajax({
		method: "PUT",
		url: "api/user/" + valId,
		dataType: "JSON",
		beforeSend: addCsrfToken(),
		data: JSON.stringify({
			username: valUsername,
			password: valPass,
			isEnabled: valEnable,
			isAccountNonLocked: valAcc

		}),
		contentType: "application/json",
		success: result => {
			console.log(result)
			$('#updateModal').modal('hide')
			$('#mitra-table').DataTable().ajax.reload()
			Swal.fire({
				position: 'top-center',
				icon: 'success',
				customClass: {
					container: 'dark'
				},
				title: 'Mitra has been saved',
				showConfirmButton: false,
				timer: 1500
			})
		}
	})
}