$(document).ready(function () {
	$('#employee-table').DataTable({
		ajax: {
			url: 'api/employee',
			dataSrc: ''
		},
		columns: [{
				data: null,
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			// {
			//     data: 'id'
			// },
			{
				data: 'name'
			},
			{
				data: 'email'
			},
			{
				data: 'phone'
			},
			{
				data: 'cv',
				render: function (data, type, row) {
					// Lakukan operasi kondisi sesuai kebutuhan
					if (data == null) {
						return 'Belum Ada CV';
					} else {
						return data;
					}
				}
			},
			{
				data: 'position.name',
				render: function (data, type, row) {
					// Lakukan operasi kondisi sesuai kebutuhan
					if (data == null) {
						return 'Belum Mapping';
					} else {
						return data;
					}
				}
			},
			{
				data: 'status',
				render: function (data, type, row) {
					// Lakukan operasi kondisi sesuai kebutuhan
					if (data === false) {
						return '<label class="badge badge-danger"> Belum Penempatan </label>';
					} else {
						return '<label class="badge badge-success"> Sudah Penempatan </label>';
					}
				}
			}
			// ,
			// {
			// 	"data": null,
			// 	render: function (data, row, type, meta) {
			// 		return `
			//         <button type="button" class="btn btn-primary px-4 mr-3"> Detail <i class="bi bi-trash"> </i></button > 
			//         `;
			// 	}
			// }

		]
	});


});