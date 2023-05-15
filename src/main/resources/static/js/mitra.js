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
			//     data: 'id'
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
				"data": null,
				render: function (data, row, type, meta) {
					return `
                    <button type="button" class="btn btn-primary px-4 mr-3"> Detail <i class="bi bi-trash"> </i></button> 
					<button type="button" class="btn btn-danger px-4 mr-3"> Non-Active <i class="bi bi-trash"> </i></button >
                    `;
				}
			}

		]
	});


});