import Model from '.';
import Config from '../Config';

export type MovimientoType = {
    key: string;
    keyCuentaOrigen: string;
    keyCuentaDestino: string;
    keyCategoria: string;
    descripcion: string;
    monto: number;
};

export default new (class movimiento {
    async create(data: MovimientoType) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(Config.api_url + '/movimiento/create', {
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

    async edit(movimiento: MovimientoType, keyMovimiento: string) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(
            Config.api_url + '/movimiento/' + keyMovimiento,
            {
                method: 'PUT',
                headers: myHeaders,
                body: JSON.stringify(movimiento),
                redirect: 'follow',
            }
        );
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }

    async delete(keyMovimiento) {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');
        myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);

        let response = await fetch(
            Config.api_url + '/movimiento/' + keyMovimiento,
            {
                method: 'DELETE',
                headers: myHeaders,
                redirect: 'follow',
            }
        );
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }

    async getbykey({keyMovimiento}) {
        return new Promise<MovimientoType[]>(async (resolve, reject) => {
            try {
                var myHeaders = new Headers();
                myHeaders.append('Content-Type', 'application/json');
                myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);
                console.log(myHeaders);
                let response = await fetch(
                    Config.api_url + '/movimiento/getbykey/' + keyMovimiento,
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
        });
    }

    async getbycuenta({keyCuenta}) {
        return new Promise<MovimientoType[]>(async (resolve, reject) => {
            try {
                var myHeaders = new Headers();
                myHeaders.append('Content-Type', 'application/json');
                myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);
                console.log(myHeaders);
                let response = await fetch(
                    Config.api_url + '/movimiento/getbycuenta/' + keyCuenta,
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
        });
    }

    async getbyuser() {
        return new Promise<MovimientoType[]>(async (resolve, reject) => {
            try {
                var myHeaders = new Headers();
                myHeaders.append('Content-Type', 'application/json');
                myHeaders.append('Authorization', 'Bearer ' + Model.user.jwt);
                console.log(myHeaders);
                let response = await fetch(
                    Config.api_url + '/movimiento/getbyuser',
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
        });
    }
})();
