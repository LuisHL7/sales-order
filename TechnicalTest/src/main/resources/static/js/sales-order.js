// Call the dataTables jQuery plugin
$(document).ready(function () {
    loadSalesOrders();
    $('#salesOrder').DataTable();
});

async function loadSalesOrders() {

    const request = await fetch('/sales-order', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const salesOrders = await request.json();

    let list = ""
    for (let sl of salesOrders) {
        let suma = 0
        for (const i in sl.products) {
            suma = suma + sl.products[i].units

        }
        let saleOrder = '<tr>\n' +
            '                     <td>' + sl.docNumber + '</td>\n' +
            '                     <td>' + getDate(sl.date) + '</td>\n' +
            '                     <td>' + sl.contactName + '</td>\n' +
            '                     <td>' + suma + '</td>\n' +
            '                     <td>' + sl.subtotal + '€</td>\n' +
            '                     <td>' + sl.total + '€</td>\n' +
            '                     <td>' + sl.status + '</td>\n' +
            '                     <td>' + sl.status + '</td>\n' +
            '                     <td>' + sl.status + '</td>\n' +
            '             </tr>'
        list += saleOrder;
    }

    document.querySelector('#salesOrder tbody').outerHTML = list

}

function getDate(seconds) {
    date = new Date(seconds * 1000);
    return date.getDate() + "/"
        + (date.getMonth() + 1) +
        "/" + date.getFullYear();
}

async function exportExcel(){
    window.open("http://localhost:8080/export-to-excel");

}

