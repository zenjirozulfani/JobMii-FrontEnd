$(document).ready(function () {
	$('#mapping-table').DataTable({
		ajax: {
			url: 'api/mapping',
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
				"data": null,
				render: function (data, row, type, meta) {
					return `
                    <a target="_blank" href="/mapping/employee" class="btn btn-primary px-4 mr-3"> Mapping </a>
                    `;
				}
			}

		]
	});


});