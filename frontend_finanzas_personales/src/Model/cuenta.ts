import Model from '.';
import Config from '../Config';

export type CuentaType = {
    key?: string,
    nombre: string;
    keyCategoria: string;
    monto: number;
};
export default new (class cuenta {
    async create(data: CuentaType) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(Config.api_url + '/cuenta/create', {
            method: 'POST',
            headers: myHeaders,
            body: JSON.stringify(data),
            redirect: 'follow',
        });
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }


    async delete({ key }) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(Config.api_url + '/cuenta/' + key, {
            method: 'DELETE',
            headers: myHeaders,
            redirect: 'follow',
        });
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }

    async addmonto(data: { key: string; monto: number }) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(Config.api_url + '/cuenta/addmonto', {
            method: 'POST',
            headers: myHeaders,
            body: JSON.stringify(data),
            redirect: 'follow',
        });
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }

    async retirarmonto(data: { key: string; monto: number }) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(Config.api_url + '/cuenta/retirarmonto', {
            method: 'POST',
            headers: myHeaders,
            body: JSON.stringify(data),
            redirect: 'follow',
        });
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }

    async getByKey({ key }): Promise<CuentaType> {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);
        let response = await fetch(Config.api_url + '/cuenta/getbykey/' + key, {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow',
        });
        if (response.status != 200) {
            throw await response.text();
        }
        return response.json();
    }

    async bytoken() {
        return new Promise<CuentaType[]>(async (resolve, reject) => {
            try {
                var myHeaders = new Headers();
                myHeaders.append('Content-Type', 'application/json');
                myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);
                // myHeaders.append("Origin", "cors");
                console.log(myHeaders);
                let response = await fetch(Config.api_url + '/cuenta/bytoken', {
                    method: 'GET',
                    headers: myHeaders,

                    // credentials: "include",
                });
                if (response.status != 200) {
                    throw await response.text();
                }
                const resp = await response.text();
                resolve(JSON.parse(resp));
            } catch (error) {
                reject(error);
            }
        });
    }
    async edit(data: CuentaType) {

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        myHeaders.append("Authorization", "Bearer " + Model.user.jwt);

        let response = await fetch(Config.api_url + "/cuenta/" + data.key, {
            method: 'PUT',
            headers: myHeaders,
            body: JSON.stringify(data),
            redirect: 'follow'
        })
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }

    
})();
