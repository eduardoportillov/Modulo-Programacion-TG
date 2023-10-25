import React, { Component } from 'react';
import { SNavigation, SPage, SText } from 'servisofts-component';
import { Btn, Container, Link } from '../../Components';
import Model from '../../Model';

export default class index extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return <SPage>
            <Container>
                <SText>CERRAR SESSION</SText>
                <SText>Seguro que quiere cerrar la ssison?</SText>
                <Btn onPress={() => {
                    Model.user.setJWT("");
                    SNavigation.reset("/");
                }}>CERRAR SESSION</Btn>
            </Container>
        </SPage>
    }
}