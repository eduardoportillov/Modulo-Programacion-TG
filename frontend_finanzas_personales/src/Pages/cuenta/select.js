import { Text, View } from 'react-native'
import React, { Component } from 'react'
import { SHr, SList, SMath, SNavigation, SPage, SText, SView } from 'servisofts-component';
import Model from '../../Model';
import { Container, Link } from '../../Components';

export default class select extends Component {
    state = {}
    constructor(props) {
        super(props);
        this.onSelect = SNavigation.getParam("onSelect");
        this.exclude = SNavigation.getParam("exclude", []);
    }

    componentDidMount() {
        this.setState({ loading: true })
        Model.cuenta.bytoken().then(e => {
            console.log(e);
            this.setState({ loading: false, data: e })
        }).catch(e => {
            console.error(e);
            this.setState({ loading: false })
        })
    }
    renderComponent(obj) {
        return <SView col={"xs-12"} card padding={8} onPress={() => {
            if (this.onSelect) this.onSelect(obj)
            // SNavigation.navigate.bind(this, "/cuenta", { pk: c.key })
        }}>
            <SText fontSize={20} bold>{obj.nombre}</SText>
            <SHr />
            <SText>Bs. {SMath.formatMoney(obj.monto?.monto)}</SText>
        </SView>
    }
    render() {
        return <SPage title={"Selecciona una cuenta"}>
            <Container loading={this.state.loading}>
                <SList buscador data={this.state.data}
                    filter={a => !this.exclude.find(b => a.key == b)}
                    render={this.renderComponent.bind(this)} />

            </Container>
        </SPage>
    }
}