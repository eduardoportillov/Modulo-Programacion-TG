import React, { Component } from 'react'
import { SList, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component'
import Model from '../../Model'
import { Container } from '../../Components'

export default class root extends Component {
    state = {}
    componentDidMount() {
        this.setState({ loading: true })
        Model.user.all().then(e => {
            console.log(e)
            this.setState({ loading: false, data: e })
        }).catch(e => {
            console.error(e)
            this.setState({ loading: false, error: e })
        })
    }
    render() {
        return <SPage title={"Lista de usuarios"}>
            <Container loading={this.state.loading}>
                <SText color={STheme.color.danger}>{this.state?.error}</SText>
                <SList data={this.state.data}
                    buscador
                    render={obj => <SView card padding={8} onPress={() => { SNavigation.navigate("/user/profile", { key: obj.key }) }}>
                        <SText bold>{obj.key}</SText>
                        <SText bold>{obj.email}</SText>
                    </SView>
                    }
                />
            </Container>
        </SPage>
    }
}