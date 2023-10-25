import { SPage, SPageListProps } from 'servisofts-component';

import Root from './root';
import list from "./list"
import profile from "./profile"
import _new from "./new"
import unlogin from "./unlogin"
export default SPage.combinePages("user", {
    "": Root,
    list,
    "new": _new,
    profile,
    unlogin
});