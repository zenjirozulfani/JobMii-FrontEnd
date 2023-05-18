$(document).ready(function () {
	$('#apply-table').DataTable({
		ajax: {
			url: 'api/apply-employee',
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
				data: 'employee.name'
			},
			{
				data: 'employee.email'
			},
			{
				data: 'employee.position.name'
			},
			{
				data: 'vacancy.mitra.name'
			},
			{
				// data: 'vacancy.title'
				data: null,
				render: function (data, row, type, meta) {
					return `<a href="/vacancy/${data.vacancy.id}">${data.vacancy.title}</a>`
				}
			},
			{
				data: 'apply_date',
				render: function (data, row, type, meta) {
					return data + ` WIB`
				}
			},
			{
				data: 'status.id',
				render: function (data, row, type) {
					if (data === 1) {
						return '<label class="badge badge-warning"> Process </label>';
					} else if (data === 2) {
						return '<label class="badge badge-danger"> Rejected </label>';
					} else {
						return '<label class="badge badge-success"> Accepted </label>';
					}
				}
			}

		]
	});

});