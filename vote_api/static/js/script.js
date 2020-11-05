'use strict';
var isVoted=false;
var oldVote=null;
var oldButtonId=null;
const vote = async (btn) => {
    let res_1;
    let res_2;
    try {
        btn.disabled =true;
        btn.querySelector('.check').style.display = 'inline';
        res_1 = await axios.post('/votes', {'option': btn.dataset.option});
        console.log(oldVote);
        if (isVoted){

            document.getElementById(oldButtonId).disabled = false;
            res_2 = await axios.delete('/votes', {data: {'option': oldVote}});
        }
        isVoted=true;
        oldButtonId=btn.id;
        oldVote=btn.dataset.option;
    } catch (err) {
        // See Handling Errors: https://github.com/axios/axios#axios-api
        if (err.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.error(`Server replied with status ${err.response.status}:\n
                ${JSON.stringify(err.response.data, null, '\t')}`);
        } else if (err.request) {
            console.error(('The request was made but no response was received'));
        } else {
            console.error(('Unspecified error'));
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('button.vote');
    buttons.forEach(btn => {
        btn.addEventListener('click', () => {
            buttons.forEach(e => {
                e.querySelector('.check').style.display = 'none';
            });
            vote(btn);
        })
    })
});
