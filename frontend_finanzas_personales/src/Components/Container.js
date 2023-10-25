import React, { Component } from 'react';
import { SLoad, STheme, SView } from 'servisofts-component';

export default class index extends Component<{ loading: boolean }> {
    render() {
        if (this.props.loading) return <SLoad />
        return (
            <SView col={"xs-12"} center {...this.props} >
                <SView col={"xs-11 sm-10 md-8 lg-6 xl-4 xxl-3"} center flex={this.props.flex}>
                    {this.props.children}
                </SView>
            </SView>
        );
    }
}
