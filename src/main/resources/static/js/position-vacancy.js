function create() {
	let valIdVacancy = $('#create-vacancy-id').val()
	let valIdPosiiton = $('#create-id-position').val()
	let valQuota = $('#create-quota').val()


	// console.log(valName);
	$.ajax({
		method: "POST",
		url: "api/position-vacancy",
		dataType: "JSON",
		beforeSend: addCsrfToken(),
		data: JSON.stringify({
			position: {

				id: valIdVacancy
			},
			vacancy: {
				id: valIdPosiiton
			},
			quota: valQuota

		}),
		contentType: "application/json",
		success: result => {
			console.log(result)
			$('#addModal').modal('hide')
			location.reload();
			Swal.fire({
				position: 'top-center',
				icon: 'success',
				title: 'Data has been added!',
				showConfirmButton: false,
				timer: 1500,
				customClass: {
					container: 'dark'
				},
			})
		}
	})
}