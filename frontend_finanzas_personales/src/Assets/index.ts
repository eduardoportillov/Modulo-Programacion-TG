import { SAssets } from 'servisofts-component';

import Logo, { ReactComponent as LogoW } from './svg/logo.svg';
import logoCompleto, { ReactComponent as logoCompletoW } from './svg/logoCompleto.svg';
import profile2, { ReactComponent as profile2W } from './svg/profile2.svg';
import Idelivery, { ReactComponent as IdeliveryW } from './svg/idelivery.svg';
import Irecoger, { ReactComponent as IrecogerW } from './svg/irecoger.svg';
import Ipago, { ReactComponent as IpagoW } from './svg/ipago.svg';
import LocationTapeke, { ReactComponent as LocationTapekeW } from './svg/locationTapeke.svg';
import MarcadorMapa, { ReactComponent as MarcadorMapaW } from './svg/marcadorMapa.svg';
import ModoLluvia, { ReactComponent as ModoLluviaW } from './svg/icono_modo_lluvia.svg';
import ModoDiaEspecial, { ReactComponent as ModoDiaEspecialW } from './svg/modo_dia_especial.svg';



const Assets: SAssets = {
    svg: {
        "Logo": { Native: Logo, Web: LogoW },
        "logoCompleto": { Native: logoCompleto, Web: logoCompletoW },
        "profile2": { Native: profile2, Web: profile2W },
        "Idelivery": { Native: Idelivery, Web: IdeliveryW },
        "Irecoger": { Native: Irecoger, Web: IrecogerW },
        "Ipago": { Native: Ipago, Web: IpagoW },
        "LocationTapeke": { Native: LocationTapeke, Web: LocationTapekeW },
        "MarcadorMapa": { Native: MarcadorMapa, Web: MarcadorMapaW },
        "ModoLluvia": { Native: MarcadorMapa, Web: ModoLluviaW },
        "ModoDiaEspecial": { Native: MarcadorMapa, Web: ModoDiaEspecialW },

    }
}

export default Assets;