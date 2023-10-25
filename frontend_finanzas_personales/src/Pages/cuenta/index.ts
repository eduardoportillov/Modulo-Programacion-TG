import { SPage, SPageListProps } from 'servisofts-component';

import _new from './new';
import edit from './edit';
import profile from './profile';
import select from './select';
import addmonto from "./addmonto"
import retirarmonto from './retirarmonto';
export default SPage.combinePages("cuenta", {
    "": profile,
    "new": _new,
    edit,
    select,
    profile,
    addmonto,
    retirarmonto
});