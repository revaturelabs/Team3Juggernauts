const axios = require('axios').default;

module.exports.demo = async function() {
    let res = await axios.post('http://localhost:8080/login/jacobgravely@icloud.com/password', {
        headers: {
            'Content-Type': 'application/json',
            'Cookie': 'JSESSION_ID=6AD753F6E747F353380F84A2E2B4C1FE'
        },
        withCredentials: true,
    });
};