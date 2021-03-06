'use strict';

let hasVoted = false;

const vote = async (btn) => {
    if (hasVoted) {
        alert('You have already voted.');
        return;
    }

    let res;
    try {
        res = await axios.post('/votes', {'option': btn.dataset.option});
        btn.querySelector('.check').style.display = 'inline';
        hasVoted = true;

    } catch (err) {
        // See Handling Errors: https://github.com/axios/axios#axios-api
        if (err.response) {
            console.error(`Server replied with status ${err.response.status}:\n
                ${JSON.stringify(err.response.data, null, '\t')}`);
        } else if (err.request) {
            console.error(('The request was made but no response was received'));
        } else {
            console.error(('Unspecified error'));
        }
        alert('An error occurred. Check the console.');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('button.vote');
    buttons.forEach(btn => {
        btn.addEventListener('click', () => {
            // buttons.forEach(e => {
            //     e.querySelector('.check').style.display = 'none';
            // });
            vote(btn);
        })
    })
});
