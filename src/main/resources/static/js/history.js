$(document).ready(function () {
	$('#history-table').DataTable({
		ajax: {
			url: 'api/history',
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
				data: 'apply_employee.employee.name'
			},
			{
				data: 'apply_employee.vacancy.mitra.name'
			},
			{
				data: null,
				render: function (data, row, type, meta) {
					return `
                    <a href="/vacancy/${data.apply_employee.vacancy.id}"> ` + data.apply_employee.vacancy.title + ` </a>`;
				}
			},
			{
				data: 'update_date'
			},
			{
				data: 'apply_employee.hr.username',
				render: function (data, row, type, meta) {
					return `
                    <p class="text-uppercase"> ` + data + `</p>`;
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