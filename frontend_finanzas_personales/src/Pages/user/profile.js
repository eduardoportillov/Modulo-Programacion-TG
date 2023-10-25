import React, { Component } from 'react'
import { SList, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component'
import Model from '../../Model'
import { Container } from '../../Components'

export default class index extends Component {
    state = {
        key: SNavigation.getParam("key")
    }
    componentDidMount() {
        this.setState({ loading: true })
        Model.user.getByKey({ "key": this.state.key }).then(e => {
            this.setState({ loading: false, data: e })
        }).catch(e => {
            this.setState({ loading: false, error: e })
        })
    }
    render() {
        return <SPage>
            <Container>
                <SText color={STheme.color.danger}>{this.state?.error}</SText>
                <SText >{JSON.stringify(this.state)}</SText>

            </Container>
        </SPage>
    }
}