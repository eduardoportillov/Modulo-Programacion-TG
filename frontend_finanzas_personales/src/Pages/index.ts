import { SPage, SPageListProps } from 'servisofts-component';

import Root from './root';
import login from './login';
import registro from './registro';
import user from './user';
import cuenta from './cuenta';
import movimiento from './movimiento';
import categoriacuenta from './categoriacuenta.js';
import categoriamovimiento from './categoriamovimiento';
import traspaso from './traspaso';
export default SPage.combinePages("/", {
    "": Root,
    "login": login,
    registro,
    ...user,
    ...cuenta,
    ...movimiento,
    categoriacuenta,
    categoriamovimiento,
    traspaso
});