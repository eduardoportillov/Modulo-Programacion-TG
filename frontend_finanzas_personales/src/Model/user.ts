import Config from '../Config';
import {SStorage} from 'servisofts-component';
export type UserType = {
    key?: string;
    email: string;
    password: string;
};
export default new (class user {
    jwt = '';
    constructor() {
        SStorage.getItem('jwt', e => {
            this.jwt = e;
        });
    }

    getUserLog() {
        if (!this.jwt) return null;
        return this.decodeJWT(this.jwt);
    }
    setJWT(_jwt) {
        this.jwt = _jwt;
        SStorage.setItem('jwt', _jwt);
    }

    async login(data: UserType): Promise<string> {
        if (!data.email) throw 'email is required.';
        if (!data.password) throw 'password is required.';

        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');

        let response = await fetch(Config.api_url + '/user/login', {
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

    async registro(data: UserType): Promise<string> {
        if (!data.email) throw 'email is required.';
        if (!data.password) throw 'password is required.';

        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');

        var raw = JSON.stringify(data);
        let response = await fetch(Config.api_url + '/user/registro', {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow',
        });
        let responseTxt = await response.text();
        if (response.status != 200) {
            throw responseTxt;
        }
        return responseTxt;
    }


    async all() {
        return new Promise<UserType[]>(async (resolve, reject) => {
            try {
                var myHeaders = new Headers();
                myHeaders.append('Content-Type', 'application/json');
                myHeaders.append('Authorization', 'Bearer ' + this.jwt);
                console.log(myHeaders);
                let response = await fetch(Config.api_url + '/user/all', {
                    method: 'GET',
                    headers: myHeaders,
                    redirect: 'follow',
                });
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

    async getByKey({key}): Promise<UserType> {
        var myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');

        let response = await fetch(Config.api_url + '/user/' + key, {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow',
        });
        if (response.status != 200) {
            throw await response.text();
        }
        return response.json();
    }

    decodeJWT(token) {
        try {
            // Dividimos el token en sus partes (header, payload, signature)
            const base64Url = token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(
                atob(base64)
                    .split('')
                    .map(function (c) {
                        return (
                            '%' +
                            ('00' + c.charCodeAt(0).toString(16)).slice(-2)
                        );
                    })
                    .join('')
            );
            const payload = JSON.parse(jsonPayload);
            if (!payload) return null;
            if (!payload.sub) return null;
            return JSON.parse(payload.sub);
        } catch (err) {
            console.error('Invalid token', err);
            return null;
        }
    }
})();
