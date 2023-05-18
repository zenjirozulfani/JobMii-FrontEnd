$(document).ready(function () {
	$('#applyEmployee-table').DataTable({
		ajax: {
			url: 'api/apply-employee-id',
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
				data: 'vacancy.title'
			},
			{
				data: 'vacancy.mitra.name'
			},
			{
				data: 'vacancy.address'
			},
			{
				data: 'apply_date',
				render: function (data, row, type, meta) {
					return data + ` WIB`
				}
			},
			{
				data: 'hr.username'
			},
			{
				data: 'status.name'
			}, {
				"data": null,
				render: function (data, row, type, meta) {
					return `
                    <button type="button" class="btn btn-primary px-4 mr-3"> Detail </button > 
                    `;
				}
			}
		]
	});
});