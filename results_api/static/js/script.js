const chart = new Chart(
    document.getElementById('chart'),
    {
        type: 'pie',
        data: {
            labels: ['Cats', 'Dogs'],
            datasets: [{
                data: [],
                backgroundColor: ["#F7464A", "#46BFBD"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1"]
            }]
        },
        options: {
            responsive: true
        }
    }
);

const getResults = async () => {
    try {
        const res = await axios.get('/results');
        const body = res.data.body;

        let options = [];
        let votes = [];
        Object.keys(body).forEach(key => {
            options.push(key);
            votes.push(body[key]);
        })
        chart.data.labels = options;
        chart.data.datasets[0].data = votes;

        chart.update();
    } catch (err) {
        if (err.response) {
            console.error(`Server replied with status ${err.response.status}:\n
                ${JSON.stringify(err.response.data, null, '\t')}`);
        } else if (err.request) {
            console.error(('The request was made but no response was received'));
        } else {
            console.error(('Unspecified error'));
        }
    }
};

window.addEventListener('DOMContentLoaded', () => {
    getResults();
    setInterval(() => {
        getResults();
    }, 5 * 1000);
});