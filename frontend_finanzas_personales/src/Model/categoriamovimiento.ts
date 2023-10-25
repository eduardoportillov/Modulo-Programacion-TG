import Model from '.';
import Config from '../Config';

export type CategoriaMovimientoType = {
    key: string;
    nombre: string;
};
export default new (class categoriamovimiento {
    async create(data: CategoriaMovimientoType) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(
            Config.api_url + '/categoriamovimiento/createuser',
            {
                method: 'POST',
                headers: myHeaders,
                body: JSON.stringify(data),
                redirect: 'follow',
            }
        );
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return JSON.parse(responseTxt);
    }
    async edit(data: CategoriaMovimientoType) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(
            Config.api_url + '/categoriamovimiento/edituser/' + data.key,
            {
                method: 'PUT',
                headers: myHeaders,
                body: JSON.stringify(data),
                redirect: 'follow',
            }
        );
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }

    async delete({key}) {
        return new Promise<string>(async (resolve, reject) => {
            try {
                var myHeaders = new Headers();
                myHeaders.append('Content-Type', 'application/json');
                myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);
                console.log(myHeaders);
                let response = await fetch(
                    Config.api_url + '/categoriamovimiento/' + key,
                    {
                        method: 'DELETE',
                        headers: myHeaders,
                        redirect: 'follow',
                    }
                );
                if (response.status != 200) {
                    throw await response.text();
                }

                const resp = await response.text();
                return resolve(resp);
            } catch (error) {
                reject(error);
            }
        });
    }
    async all() {
        return new Promise<CategoriaMovimientoType[]>(
            async (resolve, reject) => {
                try {
                    var myHeaders = new Headers();
                    myHeaders.append('Content-Type', 'application/json');
                    myHeaders.append(
                        'Authorization',
                        'Bearer ' + Model.user.jwt
                    );
                    console.log(myHeaders);
                    let response = await fetch(
                        Config.api_url + '/categoriamovimiento/all',
                        {
                            method: 'GET',
                            headers: myHeaders,
                            redirect: 'follow',
                        }
                    );
                    if (response.status != 200) {
                        throw await response.text();
                    }

                    const resp = await response.text();
                    return resolve(JSON.parse(resp));
                } catch (error) {
                    reject(error);
                }
            }
        );
    }
    async getalluser() {
        return new Promise<CategoriaMovimientoType[]>(
            async (resolve, reject) => {
                try {
                    var myHeaders = new Headers();
                    myHeaders.append('Content-Type', 'application/json');
                    myHeaders.append(
                        'Authorization',
                        'Bearer ' + Model.user.jwt
                    );
                    console.log(myHeaders);
                    let response = await fetch(
                        Config.api_url + '/categoriamovimiento/getalluser',
                        {
                            method: 'GET',
                            headers: myHeaders,
                            redirect: 'follow',
                        }
                    );
                    if (response.status != 200) {
                        throw await response.text();
                    }

                    const resp = await response.text();
                    return resolve(JSON.parse(resp));
                } catch (error) {
                    reject(error);
                }
            }
        );
    }
})();
