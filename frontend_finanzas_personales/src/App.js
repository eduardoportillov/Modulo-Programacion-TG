import React from 'react';
import { SComponentContainer, SNavigation, SText, STheme } from 'servisofts-component';
import Config from "./Config";
import Assets from './Assets';
import Pages from './Pages';
import BackgroundImage from './Components/BackgroundImage';
import { version } from "../package.json"

const App = (props) => {
    return <SComponentContainer
        debug
        background={<BackgroundImage />}
        assets={Assets}
        inputs={Config.inputs}
        theme={{ themes: Config.theme, initialTheme: "dark" }}
    >
        <SNavigation props={{ title: 'Finanzas Personales', pages: Pages }}
            linking={{
                prefixes: ["https://fp.com/app/", "http://fp.com/app/", 'fp://app/'],
            }} />
        <SText style={{ position: "absolute", bottom: 2, right: 2, }} fontSize={10} color={STheme.color.lightGray}>v{version}</SText>
    </SComponentContainer>
}
export default App;