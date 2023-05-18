$(document).ready(function () {
	$('#position-table').DataTable({
		ajax: {
			url: 'api/position',
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
			}


		]
	});
});